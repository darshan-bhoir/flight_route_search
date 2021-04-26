import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import FlightSearchComponent from "./flights/FlightSearchComponent";
import React from "react";

const AppRouter = () => {
    return(
        <div style={style}>
            <Router>
                    <Switch>
                        <Route path="/" exact component={FlightSearchComponent} />
                        <Route path="/flights" component={FlightSearchComponent} />
                    </Switch>
            </Router>
        </div>
    )
}

const style={
    marginTop:'20px'
}

export default AppRouter;