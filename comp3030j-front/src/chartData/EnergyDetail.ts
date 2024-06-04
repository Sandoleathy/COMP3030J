import axios from '@/axios'

export interface completeEnergySystemData {
    generatorSpeed: number;
    generatorTorque: number;
    currentWind: number;
    powerWind: number;
    windEnergyConversionEfficiency: number;
    theoreticalMaxPower: number;
    windSpeed: number;
    energyProducedWind: number;
    carbonReductionWind: number;
    lightIntensity: number;
    deviceTemperature: number;
    currentSolar: number;
    powerSolar: number;
    energyProducedSolar: number;
    carbonReductionSolar: number;
    waterFlow: number;
    flowSpeed: number;
    upperWaterLevel: number;
    lowerWaterLevel: number;
    turbineSpeed: number;
    currentHydro: number;
    powerHydro: number;
    carbonReductionHydro: number;
    energyProducedHydro: number;
    timeSeconds: number;
}

export let energyDataSets: completeEnergySystemData = {
    generatorSpeed: 0,
    generatorTorque: 0,
    currentWind: 0,
    powerWind: 0,
    windEnergyConversionEfficiency: 0,
    theoreticalMaxPower: 0,
    windSpeed: 0,
    energyProducedWind: 0,
    carbonReductionWind: 0,
    lightIntensity: 0,
    deviceTemperature: 0,
    currentSolar: 0,
    powerSolar: 0,
    energyProducedSolar: 0,
    carbonReductionSolar: 0,
    waterFlow: 0,
    flowSpeed: 0,
    upperWaterLevel: 0,
    lowerWaterLevel: 0,
    turbineSpeed: 0,
    currentHydro: 0,
    powerHydro: 0,
    carbonReductionHydro: 0,
    energyProducedHydro: 0,
    timeSeconds: 0,
}

function getCurrentFormattedTime(): string {
    const now = new Date();
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    const seconds = String(now.getSeconds()).padStart(2, '0');

    return `${hours}:${minutes}:${seconds}`;
}

let timestamps: string[] = []

let windWattDatasets: Number[] = []
let windCarbonDatasets: Number[] = []

export const windTurbineWattCarbon = {
    type: 'line',
    data: {
        labels: timestamps,
        datasets: [
            {
                label: 'Generator power',
                data: windWattDatasets,
                borderWidth: 1,
            },
            {
                label: 'Carbon reduced',
                borderWidth: 1,
                data: windCarbonDatasets,
            }
        ]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            y: {
                beginAtZero: true,
                title: {
                    display: true,
                    text: 'Kwh', // 在这里设置单位
                    font: {
                    size: 12,
                    weight: 'bold',
                    }
                },
                grid: {
                    display: false // 将 x 轴网格线隐藏
                }
            },
            x: {
                grid: {
                    display: false // 将 x 轴网格线隐藏
                }
            }
        },
    }
}

let windRMPDatasets: Number[] = []
let windTorqueDatasets: Number[] = []
export const windTurbineRPMTorque = {
    type: 'bar', // 基础图表类型
    data: {
        labels: timestamps,
        datasets: [{
            label: 'Generator RPM',
            data: windRMPDatasets,
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1,
            yAxisID: 'y-axis-1' // 绑定到 y-axis-1
        }, {
            label: 'Generator Torque',
            data: windTorqueDatasets,
            borderColor: 'rgba(153, 102, 255, 1)',
            backgroundColor: 'rgba(153, 102, 255, 0.2)',
            borderWidth: 1,
            type: 'line', // 指定为折线图
            yAxisID: 'y-axis-2' // 绑定到 y-axis-2
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            yAxes: [{
                type: 'linear', // 线性刻度
                display: true,
                position: 'left',
                id: 'y-axis-1', // y轴ID
                ticks: {
                    beginAtZero: true,

                },
                scaleLabel: {
                    display: true,
                    labelString: 'Units'
                },
            },
            {
                type: 'linear', // 线性刻度
                display: true,
                position: 'right',
                id: 'y-axis-2', // y轴ID
                ticks: {
                    beginAtZero: true
                },
                // 反转刻度顺序
                gridLines: {
                    drawOnChartArea: false // 仅在右侧显示线条
                }
            }]
        }
    }
}


let solarWattDatasets: Number[] = []
let solarCarbonDatasets: Number[] = []
export const solarWattCarbon = {
    type: 'line',
    data: {
        labels: timestamps,
        datasets: [
            {
                label: 'Generator power',
                data: solarWattDatasets,
                borderWidth: 1
            },
            {
                label: 'Carbon reduced',
                data: solarCarbonDatasets,
                borderWidth: 1
            }
        ]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            y: {
                beginAtZero: true,
                title: {
                    display: true,
                    text: 'Kwh', // 在这里设置单位
                    font: {
                        size: 12,
                        weight: 'bold',
                    }
                },
                grid: {
                    display: false // 将 x 轴网格线隐藏
                }
            },
            x: {
                grid: {
                    display: false // 将 x 轴网格线隐藏
                }
            }
        },
    }
}

let solarElectricDatasets: Number[] = []
let solarLightDatasets: Number[] = []
export const solarElectricLight = {
    type: 'bar', // 基础图表类型
    data: {
        labels: timestamps,
        datasets: [{
            label: 'Power generated',
            data: solarElectricDatasets,
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1,
        }, {
            label: 'Light intensity',
            data: solarLightDatasets,
            borderColor: 'rgba(153, 102, 255, 1)',
            backgroundColor: 'rgba(153, 102, 255, 0.2)',
            borderWidth: 1,
            type: 'line', // 指定为折线图
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        y: {
            beginAtZero: true,
            title: {
                display: true,
                text: 'Kwh', // 在这里设置单位
                font: {
                    size: 12,
                    weight: 'bold',
                }
            },
            grid: {
                display: false // 将 x 轴网格线隐藏
            }
        },
        x: {
            grid: {
                display: false // 将 x 轴网格线隐藏
            }
        }
    }
}

let waterWattDatasets: Number[] = []
let waterCarbonDatasets: Number[] = []
export const waterWattCarbon = {
    type: 'line',
    data: {
        labels: timestamps,
        datasets: [
            {
                label: 'Generator power',
                data: waterWattDatasets,
                borderWidth: 1
            },
            {
                label: 'Carbon reduced',
                data: waterCarbonDatasets,
                borderWidth: 1
            }
        ]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            y: {
                beginAtZero: true,
                title: {
                    display: true,
                    text: 'kwh', // 在这里设置单位
                    font: {
                        size: 12,
                        weight: 'bold',
                    }
                },
                grid: {
                    display: false // 将 x 轴网格线隐藏
                }
            },
            x: {
                grid: {
                    display: false // 将 x 轴网格线隐藏
                }
            }
        },
    }
}

let waterLevelDatasets: Number[] = [0,1]
export const waterLevel = {
    type: 'bar',
    data: {
        labels: ['Upstream','Downstream'],
        datasets: [
            {
                label: 'WaterLevel',
                data: waterLevelDatasets,
                borderWidth: 1
            },
        ]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            y: {
                beginAtZero: true,
                title: {
                    display: true,
                    text: 'm', // 在这里设置单位
                    font: {
                        size: 12,
                        weight: 'bold',
                    }
                },
                grid: {
                    display: false // 将 x 轴网格线隐藏
                }
            },
            x: {
                grid: {
                    display: false // 将 x 轴网格线隐藏
                }
            }
        },
    }
}

let waterFlowDatasets: Number[] = []
export const waterFlowRate = {
    type: 'bar',
    data: {
        labels: timestamps,
        datasets: [
            {
                label: 'Flow rate',
                data: waterFlowDatasets,
                borderWidth: 1
            },
        ]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            y: {
                beginAtZero: true,
                title: {
                    display: true,
                    text: 'm³/s', // 在这里设置单位
                    font: {
                        size: 12,
                        weight: 'bold',
                    }
                },
                grid: {
                    display: false // 将 x 轴网格线隐藏
                }
            },
            x: {
                grid: {
                    display: false // 将 x 轴网格线隐藏
                }
            }
        },
    }
}

export function connectWebSocket(wsUrl: string): WebSocket {
    timestamps = [] //重置时间戳列表

    const socket = new WebSocket(wsUrl);
  
    socket.onopen = () => {
      console.log('WebSocket connection established');
      socket.send('Hello Server!');
    };
  
    socket.onmessage = (event) => {
      //console.log('Message from server:', event.data);
      try{
          const parsedData = parseData(event.data);
          if (parsedData) {
              // 这里将解析后的数据赋值给 energyDataSets
              energyDataSets = parsedData;
              //console.log('Parsed data:', energyDataSets);

              waterLevelDatasets[0] = energyDataSets.lowerWaterLevel
              waterLevelDatasets[1] = energyDataSets.upperWaterLevel
              if(timestamps.length < 10){
                  timestamps.push(getCurrentFormattedTime())
                  windWattDatasets.push(energyDataSets.powerWind)
                  windCarbonDatasets.push(energyDataSets.carbonReductionWind)
                  windRMPDatasets.push(energyDataSets.generatorSpeed)
                  windTorqueDatasets.push(energyDataSets.generatorTorque)
                  solarWattDatasets.push(energyDataSets.powerSolar)
                  solarCarbonDatasets.push(energyDataSets.carbonReductionSolar)
                  solarElectricDatasets.push(energyDataSets.powerSolar)
                  solarLightDatasets.push(energyDataSets.lightIntensity)
                  waterWattDatasets.push(energyDataSets.powerHydro)
                  waterCarbonDatasets.push(energyDataSets.carbonReductionHydro)
                  waterFlowDatasets.push(energyDataSets.waterFlow)
              }else{
                  timestamps.shift()
                  timestamps.push(getCurrentFormattedTime())
                  windWattDatasets.shift()
                  windWattDatasets.push(energyDataSets.powerWind)
                  windCarbonDatasets.shift()
                  windCarbonDatasets.push(energyDataSets.carbonReductionWind)
                  windRMPDatasets.shift()
                  windRMPDatasets.push(energyDataSets.generatorSpeed)
                  windTorqueDatasets.shift()
                  windTorqueDatasets.push(energyDataSets.generatorTorque)
                  solarWattDatasets.shift()
                  solarWattDatasets.push(energyDataSets.powerSolar)
                  solarCarbonDatasets.shift()
                  solarCarbonDatasets.push(energyDataSets.carbonReductionSolar)
                  solarElectricDatasets.shift()
                  solarElectricDatasets.push(energyDataSets.energyProducedSolar)
                  solarLightDatasets.shift()
                  solarLightDatasets.push(energyDataSets.lightIntensity)
                  waterWattDatasets.shift()
                  waterWattDatasets.push(energyDataSets.powerHydro)
                  waterCarbonDatasets.shift()
                  waterCarbonDatasets.push(energyDataSets.carbonReductionHydro)
                  waterFlowDatasets.shift()
                  waterFlowDatasets.push(energyDataSets.waterFlow)

              }
              //更新图表数据
              windTurbineWattCarbon.data.labels = timestamps
              windTurbineWattCarbon.data.datasets[0].data = windWattDatasets
              windTurbineWattCarbon.data.datasets[1].data = windCarbonDatasets

              windTurbineRPMTorque.data.labels = timestamps
              windTurbineRPMTorque.data.datasets[0].data = windRMPDatasets
              windTurbineRPMTorque.data.datasets[1].data = windTorqueDatasets

              solarWattCarbon.data.labels = timestamps
              solarWattCarbon.data.datasets[0].data = solarWattDatasets
              solarWattCarbon.data.datasets[1].data = solarCarbonDatasets

              solarElectricLight.data.labels = timestamps
              solarElectricLight.data.datasets[0].data = solarElectricDatasets
              solarElectricLight.data.datasets[1].data = solarLightDatasets

              waterWattCarbon.data.labels = timestamps
              waterWattCarbon.data.datasets[0].data = waterWattDatasets
              waterWattCarbon.data.datasets[1].data = waterCarbonDatasets

              waterLevel.data.datasets[0].data = waterLevelDatasets

              waterFlowRate.data.labels = timestamps.slice(-3)
              waterFlowRate.data.datasets[0].data = waterFlowDatasets.slice(-3)

              //console.log(timestamps)
          } else {
              console.log('Parsed data is null or invalid');
          }
      } catch(error){
          console.error('Error parsing WebSocket message:', error);
      }
    };
  
    socket.onclose = () => {
      console.log('WebSocket connection closed');
    };
    socket.onerror = (error) => {
      console.error('WebSocket error:', error);
    };
  
    return socket;
}

function parseData(input: string): completeEnergySystemData  | null {
    const regex = /\(([^)]+)\)/;
    const match = regex.exec(input);
    if (!match) {
        console.log("Input does not contain valid data.");
        return null
    }

    const dataString = match[1];

    // 拆分每个键值对
    const pairs = dataString.split(", ");
    const data: any = {};

    // 将每个键值对转换为对象属性
    pairs.forEach(pair => {
        const [key, value] = pair.split("=");
        data[key] = parseFloat(value);
    });

    // 检查是否所有必须的字段都存在于解析后的数据中
    const requiredKeys = [
        "generatorSpeed", "generatorTorque", "currentWind", "powerWind", "windEnergyConversionEfficiency",
        "theoreticalMaxPower", "windSpeed", "energyProducedWind", "carbonReductionWind", "lightIntensity",
        "deviceTemperature", "currentSolar", "powerSolar", "energyProducedSolar", "carbonReductionSolar",
        "waterFlow", "flowSpeed", "upperWaterLevel", "lowerWaterLevel", "turbineSpeed",
        "currentHydro", "powerHydro", "carbonReductionHydro", "energyProducedHydro", "timeSeconds"
    ];

    for (const key of requiredKeys) {
        if (!(key in data)) {
            console.error(`Missing required field: ${key}`);
            return null;
        }
    }

    return data as completeEnergySystemData;
}

export default windTurbineWattCarbon