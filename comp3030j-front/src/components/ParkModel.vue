<template>
  <el-container class="container">
      <el-header class="header">
          <div class="header-controls">
              <el-page-header @back="goBack">
                  <template #content>
                      <span class="text-large font-600 mr-3">3D Model</span>
                  </template>
              </el-page-header>
              <el-button class="right-button" @click="goToViewPage">More Info</el-button>
          </div>
      </el-header>
      <el-main class="main">
          <canvas id="three"></canvas>
      </el-main>
  </el-container>
</template>


<script setup>
import * as THREE from 'three';
import { onMounted , ref } from 'vue';
import { OBJLoader } from 'three/examples/jsm/loaders/OBJLoader.js';
import { MTLLoader } from 'three/examples/jsm/loaders/MTLLoader.js';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls'; //轨道控制器，改变视角

let scene = ref()
let mesh = ref()
onMounted(() => {
  initThree()
  loadOBJ()
  loadLight()
})
import { useRouter } from 'vue-router';

// 获取 router 实例
const router = useRouter();

// 定义一个方法，用于处理返回首页的逻辑
function goBack() {
    router.push({ name: 'entry' }); // 使用 router.push 导航到首页
}

const initThree = () => {
  // 创建场景
  scene = new THREE.Scene();

  //为背景设置颜色
  scene.background = new THREE.Color('#eee')

  //获取canvas
  const canvas = document.querySelector("#three")

  const renderer = new THREE.WebGLRenderer({ canvas, antialias: true })

  //设置相机
  const camera = new THREE.PerspectiveCamera(50, window.innerWidth / window.innerHeight, 0.1, 1000)
  camera.position.set(0,300,0)

  const controls = new OrbitControls(camera, renderer.domElement)//控制器
  controls.enableDamping = true //阻尼

  //地板
  let floorGeometry = new THREE.PlaneGeometry(3000, 3000)
  let floorMaterial = new THREE.MeshPhongMaterial({color: 0x87CEEB})
  let floor = new THREE.Mesh(floorGeometry, floorMaterial)
  floor.rotation.x = -0.5 * Math.PI
  floor.receiveShadow = true
  floor.position.y = -100
  scene.add(floor)

  function animate() {
    controls.update()
    renderer.render(scene, camera)
    requestAnimationFrame(animate)

    if(resizeRendererToDisplaySize(renderer)){
      const canvas = renderer.domElement
      camera.aspect = canvas.clientWidth / canvas.clientHeight
      camera.updateProjectionMatrix()
    }


  }
  animate()
  console.log("111")
}

const resizeRendererToDisplaySize = (renderer) => {
  const canvas = renderer.domElement
  var width = window.innerWidth
  var height = window.innerHeight
  var canvasPixelWidth = canvas.width / window.devicePixelRatio
  var canvasPixelHeight = canvas.height / window.devicePixelRatio

  const needResize = canvasPixelWidth !== width || canvasPixelHeight !== height
  if (needResize) {
    renderer.setSize(width, height, false)
  }
  return needResize
}

const loadOBJ = () => {
  const mtlLoader = new MTLLoader()
  const loader = new OBJLoader()

  mtlLoader.load('/test1.mtl', function (material) {
      // 预加载
      material.preload()

      // 设置当前加载的纹理
      loader.setMaterials(material)
      loader.load('/test1.obj', loadedMesh => {
        mesh = loadedMesh
        //设置位置
        mesh.position.set(-200,0,460)
        // 添加到场景
        scene.add(mesh)
      }, (xhr) => {
        // 加载进度
        if (xhr.lengthComputable) {
          const percentComplete = xhr.loaded / xhr.total * 100
          console.log(Math.round(percentComplete, 2) + '%')
        }
      })
    })
}

const loadLight = () => {
  const dirLight = new THREE.DirectionalLight(0xffffff, 0.6)
    //光源等位置
    dirLight.position.set(-10, 8, -5)
    //可以产生阴影
    dirLight.castShadow = true
    dirLight.shadow.mapSize = new THREE.Vector2(1024, 1024)
    scene.add(dirLight)

    const hemLight = new THREE.HemisphereLight(0xffffff, 0xffffff, 0.6)
    hemLight.position.set(0, 700, 0)
    scene.add(hemLight)
}
const goToViewPage = () => {
    router.push({ name: 'data' });
};
</script>

<style>
.container {
  top: 0;
  left: 0;
  position: absolute;
  width: 100%;
  margin: auto;
}

.header-controls {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
}

.header {
  margin-top: 20px;
  width: 100%;
}

.main {
  margin: 0;
  padding-left: 50px;
  width:95%;
  /* 添加 padding, 主要是左侧留白 */
}

.text-large {
  font-size: 1.5em; /* 大号文本 */
  font-weight: 600; /* 字体加粗 */
}

.mr-3 {
  margin-right: 12px; /* 右边距 */
}

.right-button {
  margin-left: 20px; /* 在按钮和页头标题之间添加一些空间 */
}

@media (max-width: 1024px) {
  .container {
      min-width: 100vw;
  }
}
</style>
