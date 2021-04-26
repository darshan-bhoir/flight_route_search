import React, { Component } from 'react';
import { Map, GoogleApiWrapper } from 'google-maps-react';

const mapStyles = {
  width: '80%',
  height: '80%'
};

export class MapContainer extends Component {
  render() {
    return (
      <Map
        google={this.props.google}
        zoom={5}
        style={mapStyles}
        initialCenter={
          {
            lat: 25.2528,
            lng: 55.3644
          }
        }
      />
    );
  }
}

export default GoogleApiWrapper({
  apiKey: 'AIzaSyCgnpiOTnT4Ujmp7J7ITz4wwD-tlE6itmc'
})(MapContainer);