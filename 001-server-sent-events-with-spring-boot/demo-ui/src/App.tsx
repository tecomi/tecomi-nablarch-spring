import { useState } from 'react'
import './App.css'
import { fetchEventSource } from '@microsoft/fetch-event-source'

function App() {
  const [message, setMessage] = useState("...")

  const handleClick = async () => {
    // const resp = await fetch("/api/hello")
    // const { message } = await resp.json()
    // setMessage(message)

    // const es = new EventSource("/api/hello-stream")
    // es.onopen = ev => {
    //   setMessage("")
    // }
    // es.onmessage = ev => {
    //   setMessage(m => m + ev.data)
    // }

    const body = new URLSearchParams()
    body.append("name", "Uragami")
    fetchEventSource("/api/hello-stream", {
      method: "POST",
      body,
      async onopen(response) {
        setMessage("")
        return
      },
      onmessage(ev) {
        setMessage(m => m + ev.data)
      },
    })
  }

  return (
    <>
      <h1>{message}</h1>
      <div className="card">
        <button onClick={() => handleClick()}>
          HelloWorld
        </button>
        <p>
          Edit <code>src/App.tsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  )
}

export default App
