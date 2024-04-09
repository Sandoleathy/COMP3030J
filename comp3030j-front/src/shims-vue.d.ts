declare module '*.vue' {
    import { defineComponent } from 'vue';
    const component: ReturnType<typeof defineComponent>;
    export default component;
  }
  /*
  错误是因为 TypeScript 在引入 Vue 单文件组件时无法识别文件类型，从而无法进行类型检查。解决这个问题的方法是为 Vue 单文件组件添加类型声明文件。
  */