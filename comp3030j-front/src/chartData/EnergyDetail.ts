import axios from 'axios'

export const windTurbineWatt = {
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
                    text: '°C', // 在这里设置单位
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

export default windTurbineWatt