import { createContext, useContext, useState } from "react";

 const GlobalState = createContext<{todos:any[],setTodos:React.Dispatch<React.SetStateAction<any[]>>}>({
    todos:[],
    setTodos: () =>{}
 });

export const GlobalStateProvider = ({children}:{children:React.ReactNode}) => {

    const [todos, setTodos] = useState<any[]>([]);


    return (
        <GlobalState.Provider value={{todos, setTodos}}>
            {children}
        </GlobalState.Provider>
    )

}
export const useGlobalState = () => {
    const context = useContext(GlobalState);

    if(!context){
        console.log('aaaaaaaa')
    }
    console.log('contextooooooo',context)
    return context;
}