import { createSlice } from '@reduxjs/toolkit';
import { jwtDecode } from 'jwt-decode';

const token = localStorage.getItem("token");
let user = null;
if (token && token.split('.').length === 3) {
  user = jwtDecode(token);
}

const userSlice=createSlice({
    name: 'User',
    initialState: user || {},
    reducers: {
         setUser: (state, action) => {
                localStorage.setItem('token', action.payload);
                 state= action.payload;
                 return state;
         },
       
        removeUser:(state) => {
            state = {};
            return state;
        }  
    }
})


export const {  removeUser, setUser } = userSlice.actions; 
export default userSlice.reducer;
