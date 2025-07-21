import axios from "axios";


const client = axios.create({
    baseURL: import.meta.env.VITE_TODO_API,
    timeout: 5000
})

export default client;