

type Status = 'DONE'|'UNDONE'

type Priority = 'HIGH'|'LOW' | 'MEDIUM'
export type Todo  = {
    id:number,
    name:string,
    dueDate:string,
    completedAt:string,
    createdAt:string;
    status:Status,
    priority:Priority
}

export type TimeStatistics = {
    total_avg:String,
    low_avg:String,
    high_avg:String,
    medium_avg:String
}