import './App.css';
import React, {useState} from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import NavigationBar from "./components/NavigationBar";
import Home from "./components/Home";
import SideBar from "./components/SideBar";
import CompanyListComponent from "./components/CompanyListComponentUserVersion";


function App(props) {

  const [exp,setExpanded] = useState(true);
  const isUserAutorized = true;
  const isAdmin = true;
  const isCompany = false;


 return (
  <div className="App">
    <BrowserRouter>
      <NavigationBar
          toggleSideBar={() => setExpanded(!exp)}
          isUserAutorized={isUserAutorized}
          isAdmin={isAdmin}
          isCompany={isCompany}/>
      <div className="wrapper">
        <SideBar expanded={exp} />
        <div className="container-fluid main-content">
            {props.error_message && <div className="alert alert-danger m-1">{props.error_message}</div>}
            <Routes>
              <Route path="home" element={<Home />}/>
              <Route path="companies" element={<CompanyListComponent isUserAutorized={isUserAutorized}
                                                                     isAdmin={isAdmin}
                                                                     isCompany={isCompany}/>}/>
            </Routes>
        </div>
      </div>
    </BrowserRouter>
  </div>
 )
}


export default App;
