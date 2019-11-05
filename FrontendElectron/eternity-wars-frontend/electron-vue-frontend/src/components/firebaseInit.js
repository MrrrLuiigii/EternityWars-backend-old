import firebase from 'firebase';
import firebaseConfig from './firebaseConfig';
const firebaseApp = firebase.initializeApp(firebaseConfig);
firebase.analytics()
export default firebaseApp;
