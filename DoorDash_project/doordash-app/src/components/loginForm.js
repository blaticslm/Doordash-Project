import React, {Component} from 'react'; //rcc + tab

import{Form, Input, Button, Checkbox, message} from "antd";
import {UserOutlined, LockOutlined} from "@ant-design/icons";
import {login} from "../utils";

class LoginForm extends Component {
    state = { //初始状态
        loading:false,
    };

    onFinish = (values) => { //定义为函数
        console.log('Received values of form: ', values);
        this.setState({loading:true}); //loading button is true

        login(values)
            .then(() => {
                //login succesfuly
                //inform user
                //inform parent component App logged in
                //子传父路径
                message.success("Login successful");
                this.props.onSuccess();
            })
            .catch( err => {
                //login fail
                //inform user
                console.log("sdsdsd")
                message.error(err.message);
            })
            .finally( () => {
                //do whatever
                //set button to not loading ---> reset button
                this.setState({loading:false});
            });
    };

    render() {
        return (
            <Form
                name="normal_login"
                style = {{
                    width:"300px",
                    margin:"auto"
                }}
                onFinish={this.onFinish}
            >
                <Form.Item
                    name="username"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your Username!',
                        },
                    ]}
                >
                    <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Username" />
                </Form.Item>
                <Form.Item
                    name="password"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your Password!',
                        },
                    ]}
                >
                    <Input.Password //小眼睛
                        prefix={<LockOutlined className="site-form-item-icon" />}
                        type="password"
                        placeholder="Password"
                    />
                </Form.Item>


                <Form.Item>
                    <Button type="primary" htmlType="submit" loading = {this.state.loading}>
                        Log in
                    </Button>
                </Form.Item>
            </Form>
        );
    }
}

export default LoginForm; //向外公开