import axios from 'axios'

export const windTurbineWattCarbon = {
    type: 'line',
    data: {
        labels: ['Red', 'Blue', 'Yellow'],
        datasets: [
            {
                label: '# of Votes',
                data: [12, 19, 3],
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

export const windTurbineRPMTorque = {
    type: 'bar', // 基础图表类型
    data: {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
        datasets: [{
            label: 'Bar Dataset',
            data: [65, 59, 80, 81, 56, 55, 40],
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1,
            yAxisID: 'y-axis-1' // 绑定到 y-axis-1
        }, {
            label: 'Line Dataset',
            data: [28, 48, 40, 19, 86, 27, 90],
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

export function connectWebSocket(wsUrl: string): WebSocket {
    const socket = new WebSocket(wsUrl);
  
    socket.onopen = () => {
      console.log('WebSocket connection established');
      socket.send('Hello Server!');
    };
  
    socket.onmessage = (event) => {
      console.log('Message from server:', event.data);
    };
  
    socket.onclose = () => {
      console.log('WebSocket connection closed');
    };
  
    socket.onerror = (error) => {
      console.error('WebSocket error:', error);
    };
  
    return socket;
}

export default windTurbineWattCarbon