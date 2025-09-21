import { configureStore } from "@reduxjs/toolkit";
import jwtReducer from "./Slices/JwtSlice.tsx";
import userReducer from "./Slices/UserSlice.tsx"
// eslint-disable-next-line react-refresh/only-export-components
export default configureStore({
    reducer:{
        jwt: jwtReducer,
        user:userReducer
    }
})


