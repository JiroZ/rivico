import './App.css';
import NavbarRouter from "./Main/Elements/Navbar/NavbarRouter.jsx"
import {LoadAuthResponse, LoadAuthToken} from "./Main/ReduxUtils/ReduxPersist";
import {IsUserAuthValid} from "./Managers/UserManager";
import {useDispatch} from "react-redux";
import {InitAuthResponse, SignIn} from "./Redux/UserLogin/Actions";
import axios from "axios";

function App() {
    const dispatch = useDispatch();

    (function () {
        const token = LoadAuthToken()
        if (IsUserAuthValid()) {
            axios.defaults.headers.common['Authorization'] = "Bearer " + token;
        } else {
            delete axios.defaults.headers.common['Authorization'];
        }
    })();

    if(IsUserAuthValid()) {
        dispatch(SignIn())
        dispatch(InitAuthResponse(LoadAuthResponse()))
    }

    return (
        <div className="App">
            <NavbarRouter/>
        </div>
    );
}
export default App;
