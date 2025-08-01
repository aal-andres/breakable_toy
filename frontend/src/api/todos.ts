import type { Todo } from "../types/todo"
import client from "./httpService"

export const addTodo = async (todo:any) => {
    const response = await client.post('todos', todo)
    console.log('todous',todo)
}


export const getTodosAndTImeMterics = async() => {
    const response = await client.get(`todos`);
    return response.data;
}

export const getFilteredTodos = async(name:string,priority:string,status:string):Promise<any> => {
    const response = await client.get(`todos?name=${name}&status=${status}&priority=${priority}`)
    return response.data;
}

export const getTodosByPage = async(page:number) => {
    const response = await client.get(`todos?page=${page}`)
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

export const deleteTodo = async(id:number):Promise<boolean> => {
    const response = await client.delete(`todos/${id}`)
    return response.data;
}

export const updateTodo = async (id:number,todo:any) => {
    const response = await client.put(`todos/${id}`,todo);
    return response.data;
}