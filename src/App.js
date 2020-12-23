import './App.css';
import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Login from './pages/Login';
import Home from './pages/Home';
import Transfer from './pages/Transfer';



function App() {
  return (
    <>
      <Router>
        <Switch>
          <Route path='/' exact component={Login}></Route>
          <Route path='/home' exact component={Home}></Route>
          <Route path ='/transfer' exact component = {Transfer}></Route>
        </Switch>
      </Router>
    </>
  );
}

export default App;
