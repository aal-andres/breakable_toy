export default function TimeMetrics(){
    return(
        <div className="time_metrics_container">

            <div className="average_time_container">
                <h3>Average time to finish tasks</h3>
                <span>23:45 minutes</span>
            </div>
            <div className="average_priority_container">
                <h3>Average time to finish tasks by priority</h3>
                <li><span>low: </span><span>11:45 minutes</span></li>
                <li><span>low: </span><span>11:45 minutes</span></li>
                <li><span>low: </span><span>11:45 minutes</span></li>
            </div>

        </div>
    )
}