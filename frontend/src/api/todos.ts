import type { todo } from "../types/todo"
import client from "./httpService"

export const addTodo = async (todo:any) => {
    const response = await client.post('todos', todo)
    console.log(response)
}

export const getFilteredTodos = async(name:string,priority:string,status:string):Promise<todo[]> => {
    const response = await client.get(`todos?name=${name}&status=${status}&priority=${priority}`)
    console.log(response)
    return response.data;
}

export const checkTodo = async(id:number) => {
    const response = await client.post(`todos/${id}/done`)
    return response.data;
}


export const unCheckTodo = async(id:number) => {
    const response = await client.put(`todos/${id}/undone`)
    return response.data;
}

export const deleteTodo = async(id:number):Promise<todo> => {
    const response = await client.delete(`todos/${id}`)
    return response.data;
}