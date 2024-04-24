import axios from 'axios'

export const planetChartData = {
    type: 'line',
    data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [
            {
                label: '# of Votes',
                data: [12, 19, 3, 5, 2, 3],
                borderWidth: 1
            },
            {
                label: '# of Votes',
                data: [24, 16, 19, 15, 22, 34],
                borderWidth: 1
            }
    ]
    },
    options: {
        scales: {
            y: {
              beginAtZero: true
            }
        }
    },
}

export default planetChartData;