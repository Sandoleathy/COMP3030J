import axios from '@/axios/index.js'

export const planetChartData = {
    type: 'pie',
    data: {
        labels: ['Wind Turbine', 'Solar Panel', 'Hydraulic Generator'],
        datasets: [
            {
                label: 'Carbon reduced(kg)',
                data: [1,2,3],
                borderWidth: 1
            },
    ]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            y: {
                display: false,
                grid: {
                    display: false
                }
            },
            x: {
                display: false,
                grid: {
                    display: false
                }
            }
        }
    },
}

export function GetEnergyData(){
    //console.log('getdata')
    const token = sessionStorage.getItem('token')
    axios.get('/api/statistics/energy/dataFlow', {
        headers: {
            'Authorization': 'Bearer ' + token
        }
    }).then((response) => {
        console.log(response.data)
        const data = response.data;
        const dataset: number[] = []
        dataset.push(data.energySystemDataFlow[0].carbonReductionWind)
        dataset.push(data.energySystemDataFlow[0].carbonReductionSolar)
        dataset.push(data.energySystemDataFlow[0].carbonReductionHydro)
        planetChartData.data.datasets[0].data = dataset
    }).catch(error => {
        console.log(error)
    })
}

export default planetChartData;