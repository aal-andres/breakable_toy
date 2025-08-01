import PaginationButton from './PaginationButton'
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

describe("next page", () => {
    let getTodosByPageSpy;
    //const functMock = vi.fn()
    beforeEach(()=>{
        vi.clearAllMocks()
        getTodosByPageSpy = vi.spyOn(TodosApi,'getTodosByPage')
    })

    it("increases pagination count",async()=>{

        getTodosByPageSpy.mockResolvedValueOnce({
           todos: [
               { id: 1, name: 'Mock Todo 1', status: 'DONE' },
               { id: 2, name: 'Mock Todo 2', status: 'UNDONE' },
           ], 
           timeMetrics: {},
           has_next_page: false 
        });


        render(<PaginationButton></PaginationButton>)
        const button = screen.getByRole("button")
        const next_page = screen.getByTestId("next_button")

        expect(button.textContent).toEqual("1")
        await userEvent.click(next_page)
        expect(button.textContent).toEqual("2")

        await waitFor(()=>{
            expect(getTodosByPageSpy).toHaveBeenCalledTimes(1);
        })
        

    })

    it('hides button if next page is false', async()=>{
        getTodosByPageSpy.mockResolvedValueOnce({
            todos: [],
            timeMetrics: {},
            has_next_page: false 
        });
        renderWithContext(<PaginationButton></PaginationButton>)
        const next_page = screen.queryByTestId("next_button")
        await userEvent.click(next_page)
        await waitFor(()=>{
            expect(getTodosByPageSpy).toHaveBeenCalledTimes(1);
            expect(screen.queryByTestId('next_button')).not.toBeInTheDocument()
        })
    })
    it('hides prev button if count is equals to one', async()=>{
         getTodosByPageSpy.mockResolvedValueOnce({
            todos: [],
            timeMetrics: {},
            has_next_page: true 
        });
        getTodosByPageSpy.mockResolvedValueOnce({
            todos: [],
            timeMetrics: {},
            has_next_page: true 
        });
        renderWithContext(<PaginationButton></PaginationButton>)
        const button = screen.getByRole("button")
        const next_page = screen.queryByTestId("next_button")
        await userEvent.click(next_page)
        expect(button.textContent).toEqual("2")
        const prev_page = screen.queryByTestId("prev_button")
        await userEvent.click(prev_page)
        expect(button.textContent).toEqual("1")
        await waitFor(()=>{
            expect(getTodosByPageSpy).toHaveBeenCalledTimes(2);
            expect(screen.queryByTestId('prev_button')).not.toBeInTheDocument()
        })
    })


})


