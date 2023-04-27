import React from 'react';
import { Navbar, Nav } from 'react-bootstrap'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faHome, faBars, faBuilding} from '@fortawesome/free-solid-svg-icons'
import { Link } from 'react-router-dom';

 const NavigationBar = (props) => {
    return (
        <Navbar bg="light" expand="lg">
            <button type="button"
                    className="btn sideBarButton btn-outline-secondary mr-2"
                    onClick={props.toggleSideBar}>
                <FontAwesomeIcon icon={ faBars} />
            </button>
            <Navbar.Brand as={Link} to="/home"><FontAwesomeIcon icon={faHome} className=''  />{' '}Информационная система для частного строительства</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav" className={""}>
                <Nav className="ms-auto me-2">
                    { props.isUserAutorized && props.isAdmin && <Nav.Link className={"mt-1"}>Администратор \ cophis.admin@yandex.ru</Nav.Link>}
                    { props.isUserAutorized && props.isCompany && <Nav.Link className={"mt-1"}>Компания \ terem@yandex.ru</Nav.Link>}
                    { props.isUserAutorized && !props.isAdmin && !props.isCompany && <Nav.Link className={"mt-1"}>gaplikovas@student.bmstu.ru</Nav.Link>}
                    { props.isUserAutorized && <Nav.Item><Nav.Link className={"my-nav-item me-2"} as={Link} to="/me">Личный кабинет</Nav.Link></Nav.Item>}
                    { !props.isUserAutorized && <Nav.Item><Nav.Link className={"my-nav-item me-2"} as={Link} to="/login">Войти</Nav.Link></Nav.Item>}
                    { !props.isUserAutorized &&<Nav.Item><Nav.Link className={"my-nav-item me-3"} as={Link} to="/registration">Регистрация</Nav.Link></Nav.Item>}
                </Nav>
            </Navbar.Collapse>
        </Navbar>
    );
}

export default NavigationBar;