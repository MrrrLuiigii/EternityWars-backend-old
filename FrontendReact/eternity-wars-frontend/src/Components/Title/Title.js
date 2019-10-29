import React, { Component } from 'react';
import '../Title/Title.css';

class Title extends Component {
    render() {
      return (
      <div className="HeaderContent">
         <img className="HeaderImg" src={process.env.PUBLIC_URL + "/assets/Logo.png" }alt="Logo"/>
      </div>
      )
    }
  }

  export default Title;