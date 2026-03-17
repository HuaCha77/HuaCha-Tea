// 页面加载完成后初始化图表
document.addEventListener('DOMContentLoaded', function() {
    // 教师视图 - 评分分布图
    const ratingChartElement = document.getElementById('ratingChart');
    if (ratingChartElement) {
        const ratingCtx = ratingChartElement.getContext('2d');
        new Chart(ratingCtx, {
            type: 'doughnut',
            data: {
                labels: ['优秀(8-10)', '良好(6-8)', '及格(4-6)', '不及格(<4)'],
                datasets: [{
                    data: [45, 30, 20, 5],
                    backgroundColor: ['#00B894', '#FFD93D', '#FF6B6B', '#A4AF86']
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: { position: 'bottom' }
                }
            }
        });
    }

    // 教师视图 - 学期趋势图
    const trendChartElement = document.getElementById('trendChart');
    if (trendChartElement) {
        const trendCtx = trendChartElement.getContext('2d');
        new Chart(trendCtx, {
            type: 'line',
            data: {
                labels: ['2023春', '2023秋', '2024春', '2024秋'],
                datasets: [{
                    label: '平均评分',
                    data: [7.8, 8.1, 8.3, 8.5],
                    borderColor: '#667eea',
                    backgroundColor: 'rgba(102, 126, 234, 0.1)',
                    tension: 0.4
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: { position: 'top' }
                },
                scales: {
                    y: { beginAtZero: true, max: 10 }
                }
            }
        });
    }

    // 管理员视图 - 系统统计图
    const systemChartElement = document.getElementById('systemChart');
    if (systemChartElement) {
        const systemCtx = systemChartElement.getContext('2d');
        new Chart(systemCtx, {
            type: 'bar',
            data: {
                labels: ['学生', '教师', '课程', '评价完成'],
                datasets: [{
                    label: '数量',
                    data: [150, 30, 50, 120],
                    backgroundColor: ['#667eea', '#764ba2', '#f093fb', '#4ecdc4']
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: { display: true }
                }
            }
        });
    }
});