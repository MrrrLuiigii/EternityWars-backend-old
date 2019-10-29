import React, { Component } from 'react';

import firebase from 'firebase';
import StyledFirebaseAuth from 'react-firebaseui/StyledFirebaseAuth'

firebase.initializeApp({
  apiKey: "AIzaSyCcB8F67rgoWadhlaLG2diyqdlAdLrpltQ",
  authDomain: "react-auth-ffa80.firebaseapp.com"
})

class Login extends Component{
  state={
    isSignedIn: false
  }



  

  uiConfig = {
    signInFlow: "popup",
    signInOptions: [
      firebase.auth.GoogleAuthProvider.PROVIDER_ID,
    ],
    callbacks: {
      signInSucces: () => false
    }
  }

componentDidMount = () =>{
  firebase.auth().onAuthStateChanged(user => {
    this.setState({
      isSignedIn:!!user
    });
});
}

  render(){
    return(
      <div className="App">
        {this.state.isSignedIn ? (
           <span>
            <button onClick={() => firebase.auth().signOut()}>Sign Out</button>
            <h1>Welcome {firebase.auth().currentUser.displayName}</h1>
          </span>
    ):( 
           <StyledFirebaseAuth 
           uiConfig={this.uiConfig}
           firebaseAuth={firebase.auth()}
           />
    )}
    </div>
    )
  }
}
  export default Login; 