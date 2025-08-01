import AddTodo from './AddTodo'
import App from '../App'

import {render,screen, waitFor} from "@testing-library/react"
import {describe,it,expect,vi, beforeEach} from "vitest"
import "@testing-library/jest-dom/vitest"
import userEvent from "@testing-library/user-event"
import axios from 'axios'
import * as TodosApi from  '../api/todos'
import { GlobalStateProvider } from '../state/todo-context'


const renderWithContext = (ui,options) => {
    return render(ui, { wrapper: GlobalStateProvider, ...options });
}





describe('should add or update todo',()=>{
    let addTodoFunction;
    let updateTodoFunction;

     beforeEach(()=>{
            vi.clearAllMocks()
            addTodoFunction = vi.spyOn(TodosApi,'addTodo')
            updateTodoFunction = vi.spyOn(TodosApi,'updateTodo')
        })

    it('adds todo',async()=>{
        renderWithContext(<App></App>)
        const open_modal = screen.getByTestId("modal")
        await userEvent.click(open_modal)
        renderWithContext(<AddTodo></AddTodo>)
        //const addTodoModal = screen.getAllByAltText("modal-container")
        expect(screen.queryByTestId('modal-container')).toBeInTheDocument()
    })
})