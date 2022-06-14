import logo from './logo.svg';
import { Layout, Typography } from "antd";
import React, {useState} from "react"
import LoginForm from "./components/loginForm";
import SignupForm from "./components/SignUpForm";
import MyCart from "./components/MyCart";
import FoodList from "./components/FoodList";
import './App.css';



const{Header, Content} = Layout;
const{Title} = Typography;

function App() {
    //函数组件要hook
    const [authed, setAuthed] = useState(false); //没登入的时候

  return (
      <Layout style = {{height: "100vh"}}>
        <Header>
            <div className="header">
                <Title level={2} style={{color:"white", lineHeight:"inherit", marginBottom:0, fontWeight: "bold", fontSize:35, fontFamily:"Times New Roman"}}>
                    Food Order
                </Title>
                <div>
                    {
                        authed? <MyCart />:<SignupForm />
                    }
                </div>
            </div>

        </Header>
        <Content style = {{
          padding:"50px",
          maxHeight:"calc(100% - 64px)",
          overflowY:"auto"
        }}>
            { //判断是否logged in了。我要把loginForm的信息回传给app这个函数
                authed? <div> <FoodList /></div> : <LoginForm onSuccess={()=>setAuthed(true)} />  //回到loginform是第20行
            }
        </Content>
      </Layout>
  );
}

export default App;
