import React, { Component } from 'react'
import ApiService from "../../service/ApiService";
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import TextField from '@material-ui/core/TextField';
import MenuItem from '@material-ui/core/MenuItem';
import Grid from '@material-ui/core/Grid';
import MapContainer from './MapContainer';

class ListUserComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            flights: [],
            depAirport: '',
            arrAirport: '',
            users: [],
            message: null
        }
        this.searchFlights = this.searchFlights.bind(this);
        this.reloadUserList = this.reloadUserList.bind(this);
        this.handleArrAirportChange = this.handleArrAirportChange.bind(this);
        this.handleDepAirportChange = this.handleDepAirportChange.bind(this);
    }

    componentDidMount() {
        this.reloadUserList();
    }

    reloadUserList() {
        if(!this.isNullOrUndefined(this.state.depAirport) && !this.isNullOrUndefined(this.state.arrAirport)){
            let request = {depAirport: this.state.depAirport, arrAirport: this.state.arrAirport};
            ApiService.searchFlights(request)
                .then(res =>{
                    console.log('response received ',res);
                    this.setState({flights: res.data})
                });
        }
    }

    handleDepAirportChange(event){
        console.log('Event in dep airport change method is ',event);
        window.localStorage.setItem("depAirport", event.target.value);
        this.setState({
            depAirport: event.target.value
        })
      };

    handleArrAirportChange(event){
        console.log('Event in Arrival airport change method is ',event);
        window.localStorage.setItem("arrAirport", event.target.value);
        this.setState({
            arrAirport: event.target.value
        })
      };

    searchFlights() {
        console.log('State is ',this.state);
        this.reloadUserList();
    }

    isNullOrUndefined(input){
        if(input === null || input === undefined || input === ''){
            return true;
        }else return false;
    }

    render() {
        console.log('Flights data ',this.state.flights);
        return (
            <div className="grid-search-div">
                <Typography variant="h4" style={style}>Flight Details</Typography>
                <div>
                <Grid container spacing={12}>
                <Grid item xs={4}>
                <TextField
                    id="standard-select-dep-airport"
                    select
                    label="Select"
                    value={this.state.depAirport}
                    onChange={this.handleDepAirportChange}
                    helperText="Please select your departure airport"
                    >
                    {departureAirports.map((option) => (
                        <MenuItem key={option.value} value={option.value}>
                        {option.label}
                        </MenuItem>
                    ))}
                </TextField>
                </Grid>
                <Grid item xs={4}>
                <TextField
                    id="standard-select-arr-airport"
                    select
                    label="Select"
                    value={this.state.arrAirport}
                    onChange={this.handleArrAirportChange}
                    helperText="Please select your Arrival airport"
                    >
                    {arrivalAirports.map((option) => (
                        <MenuItem key={option.value} value={option.value}>
                        {option.label}
                        </MenuItem>
                    ))}
                </TextField>
                </Grid>
                <Grid item xs={4} className="search-flights">
                <Button variant="contained" color="primary" className="search-flights" onClick={() => this.searchFlights()}>
                    Search
                </Button>
                </Grid>
                </Grid>
                </div>

                <div className="grid-search-div">
                <Grid>
                <Grid item xs={12}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Onward Flight</TableCell>
                            <TableCell>Departure Airport</TableCell>
                            <TableCell>Arrival Airport</TableCell>
                            <TableCell>Departure Time</TableCell>
                            <TableCell>Arrival Time</TableCell>
                            <TableCell>Connecting Flight</TableCell>
                            <TableCell>Departure Airport</TableCell>
                            <TableCell>Arrival Airport</TableCell>
                            <TableCell>Departure Time</TableCell>
                            <TableCell>Arrival Time</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {this.state.flights.map(row => (
                            <TableRow key={row.id}>
                                {/* <TableCell component="th" scope="row">
                                    {row.id}
                                </TableCell> */}
                                <TableCell>{row.onwardFltNo}</TableCell>
                                <TableCell>{row.onwardDepArpt}</TableCell>
                                <TableCell>{row.onwardArrArpt}</TableCell>
                                <TableCell>{row.onwardDepTime}</TableCell>
                                <TableCell>{row.onwardArrTime}</TableCell>
                                <TableCell>{row.connFltNo}</TableCell>
                                <TableCell>{row.connDepArpt}</TableCell>
                                <TableCell>{row.connArrArpt}</TableCell>
                                <TableCell>{row.connDepTime}</TableCell>
                                <TableCell>{row.connArrTime}</TableCell>
                                {/* <TableCell align="right" onClick={() => this.editUser(row.id)}><CreateIcon /></TableCell>
                                <TableCell align="right" onClick={() => this.deleteUser(row.id)}><DeleteIcon /></TableCell> */}

                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
                </Grid>
                </Grid>
                </div>
                <div>
                    <MapContainer />

                </div>
            </div>
        );
    }

}

const arrivalAirports = [
    {
        value: 'BOM',
        label: 'BOM',
      },
      {
        value: 'DXB',
        label: 'DXB',
      },
      {
        value: 'JFK',
        label: 'JFK',
      },
      {
        value: 'IAH',
        label: 'IAH',
      }
  ];

  const departureAirports = [
    {
      value: 'BOM',
      label: 'BOM',
    },
    {
      value: 'DXB',
      label: 'DXB',
    },
    {
      value: 'JFK',
      label: 'JFK',
    },
    {
      value: 'IAH',
      label: 'IAH',
    }
  ];

const style ={
    display: 'flex',
    justifyContent: 'center'
}

export default ListUserComponent;