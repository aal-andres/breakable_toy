import { useGlobalState } from "../state/todo-context"

export default function TimeMetrics(){
    const {timeStatistics} = useGlobalState()
    return(
        <div className="time_metrics_container">

            <div className="average_time_container">
                <h3>Average time to finish tasks</h3>
                <span>{timeStatistics!.total_avg} minutes</span>
            </div>
            <div className="average_priority_container">
                <h3>Average time to finish tasks by priority</h3>
                <div className="list-container">
                <li><span>low: </span><span>{timeStatistics!.low_avg} minutes</span></li>
                <li><span>medium: </span><span>{timeStatistics!.medium_avg} minutes</span></li>
                <li><span>high: </span><span>{timeStatistics!.high_avg} minutes</span></li>

                </div>
            </div>

        </div>
    )
}