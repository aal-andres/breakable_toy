import { useState } from 'react'
import './App.css'
import SearchBar from './components/SearchBar'
import TodoTable from './components/TodoTable'
import TimeMetrics from './components/TimeTracker'
import AddTodo from './components/AddTodo'

function App() {
  
  

  return (
    <>
      <AddTodo active={true}/>
      <div className='table'>

      <SearchBar/>


      <button className='add_button'>Add Todo +</button>

      <TodoTable/>

      <TimeMetrics/>


      </div>
    </>
  )
}

export default App
