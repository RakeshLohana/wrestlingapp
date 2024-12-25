import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./components/Login";
import Register from './components/Register';
import Upload from './components/Upload';
import Videos from './components/Videos';

function App() {
  return (
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<Login />}>  </Route>
      <Route path="/login" element={<Login />}>  </Route>
      <Route path="/upload" element={<Upload />}>  </Route>
      <Route path="/videos" element={<Videos />}>  </Route>


    </Routes>
  </BrowserRouter>
  );
}

export default App;
