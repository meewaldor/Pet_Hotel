/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

// Import the functions you need from the SDKs you need
import { initializeApp } from "https://www.gstatic.com/firebasejs/10.8.1/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/10.8.1/firebase-analytics.js";
import { signOut, getAuth, signInWithPopup, GoogleAuthProvider } from "https://www.gstatic.com/firebasejs/10.8.1/firebase-auth.js";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyCFKl6GquMwiq_KCINJL36cPPhS2diiFrg",
    authDomain: "pethotel-415903.firebaseapp.com",
    projectId: "pethotel-415903",
    storageBucket: "pethotel-415903.appspot.com",
    messagingSenderId: "604937542220",
    appId: "1:604937542220:web:aaf2aa76d831bf5c3672e9",
    measurementId: "G-MR5RM0BMBM"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);
// Initialize the FirebaseUI Widget using Firebase.
var ui = new firebaseui.auth.AuthUI(getAuth());

const provider = new GoogleAuthProvider();
const auth = getAuth();

var uiConfig = {
    callbacks: {
        signInSuccessWithAuthResult: function (authResult, redirectUrl) {
            // User successfully signed in.
            // Return type determines whether we continue the redirect automatically
            // or whether we leave that to developer to handle.
            var user = authResult.user;
            if (user) {
                var email = user.email;
                console.log(email);
                var displayName = user.name;
                var img = user.picture;
            }
            window.location('home.jsp');
            return true;
        },
        uiShown: function () {
            // The widget is rendered.
            // Hide the loader.
            document.getElementById('loader').style.display = 'none';
        }
    },
    // Will use popup for IDP Providers sign-in flow instead of the default, redirect.
    signInFlow: 'redirect',
    signInSuccessUrl: 'home.jsp',
    signInOptions: [
        // Leave the lines as is for the providers you want to offer your users.
        firebase.auth.GoogleAuthProvider.PROVIDER_ID,
    ],
};
// The start method will wait until the DOM is loaded.
ui.start('#firebaseui-auth-container', uiConfig);

//signInWithPopup(auth, provider)
//  .then((result) => {
//    // This gives you a Google Access Token. You can use it to access the Google API.
//    const credential = GoogleAuthProvider.credentialFromResult(result);
//    const token = credential.accessToken;
//    // The signed-in user info.
//    const user = result.user;
//            console.log(user.email);
//    // IdP data available using getAdditionalUserInfo(result)
//    // ...
//  }).catch((error) => {
//    // Handle Errors here.
//    const errorCode = error.code;
//    const errorMessage = error.message;
//    // The email of the user's account used.
//    const email = error.customData.email;
//    // The AuthCredential type that was used.
//    const credential = GoogleAuthProvider.credentialFromError(error);
//    // ...
//  });


signOut(auth).then(() => {
    // Sign-out successful.
}).catch((error) => {
    // An error happened.
});
