

type Status = 'DONE'|'UNDONE'

type Priority = 'HIGH'|'LOW' | 'MEDIUM'
export type todo  = {
    id:number,
    name:string,
    due_date:string,
    completed_at:string
    status:Status,
    priority:Priority
}