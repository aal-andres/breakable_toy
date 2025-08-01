import { useState } from 'react'
import './App.css'
import SearchBar from './components/SearchBar'
import TodoTable from './components/TodoTable'
import TimeMetrics from './components/TimeTracker'
import AddTodo from './components/AddTodo'
import { GlobalStateProvider, useGlobalState } from './state/todo-context'
import PaginationButton from './components/PaginationButton'
import ResetButton from './components/ResetButton'

function App() {
  
  
 return(
  <GlobalStateProvider>
    <AppState></AppState>
  </GlobalStateProvider>
 )

  
}

function AppState(){

  const {setOpen} = useGlobalState();
  return (
    <>
<div className='app-container'>

      <AddTodo/>
      <SearchBar/>
      <div className='table'>

      <div className='app-action-buttons'>

      <button data-testid="modal" className='add_button' onClick={()=> setOpen(true)}>Add Todo +</button>
      <ResetButton/>
      </div>

      <TodoTable/>
      </div>
      <PaginationButton></PaginationButton>
      


      <TimeMetrics/>

</div>

    </>
  )
}

export default App
