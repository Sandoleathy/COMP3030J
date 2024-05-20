import axios from 'axios'

export const planetChartData = {
    type: 'pie',
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
              
            }
        }
    },
}

export default planetChartData;