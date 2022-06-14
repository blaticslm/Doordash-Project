import React, {Component} from 'react';
import {Button, Modal, Form, message, Input} from "antd"
import {signup} from "../utils";
import {LockOutlined, UserOutlined} from "@ant-design/icons"; //lockOutline是密码圈圈， useroutline没有圈圈


class SignUpForm extends Component {
    state = {
        displayModal: false //用于弹窗来显示注册信息
    };

    signupOnClick = () => {
        this.setState({
            displayModal:true
        })
    };

    handleCancel = () => {
        this.setState({
            displayModal:false
        })
    };

    onFinish =(new_user_data) => {
        signup(new_user_data)
            .then(() => {
                this.setState({
                    displayModal:false
                });
                message.success("Sign up successfully!")
            })
            .catch(err => {
                message.error(err.message)
            });
    };

    render() {
        return (
            <div>
                <Button shape = "square" type="primary" onClick={this.signupOnClick}>
                    Register
                </Button>

                <Modal title="Register" visible = {this.state.displayModal} onCancel={this.handleCancel} footer={null} destroyOnClose={true}>
                    <Form name="Normal Registration" initialValues={{remember:true}} onFinish={this.onFinish} preserve={false}>

                        <Form.Item name="email"  rules={[{required:true, message:"Please enter your email!"}]}>
                            <Input prefix={<UserOutlined />} placeholder="Email" />
                        </Form.Item>

                        <Form.Item name="password"  rules={[{required:true, message:"Please enter your password"}]}>
                            <Input prefix={<LockOutlined />} placeholder="Password" />
                        </Form.Item>

                        <Form.Item name="firstname"  rules={[{required:true, message:"Please enter your firstname!"}]}>
                            <Input  placeholder="Firstname" />
                        </Form.Item>

                        <Form.Item name="lastname" rules={[{required:true, message:"Please enter your lastname"}]}>
                            <Input placeholder="Lastname" />
                        </Form.Item>

                        <Form.Item>
                            <Button type = "primary" htmlType="submit" style={{ marginLeft: 20 }}>
                                Register
                            </Button>
                        </Form.Item>
                    </Form>
                </Modal>
            </div>
        )
    }
}

export default SignUpForm;