import React from 'react';
import { Link } from 'react-router-dom'
import { Nav } from 'react-bootstrap'
import {faBuilding, faHammer} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

const SideBar = props => {
    return (
        <>
        { props.expanded &&
            <Nav className={"flex-column my-sidebar my-sidebar-expanded"}>
                <Nav.Item><Nav.Link className={"side-bar-item"} as={Link} to="/companies"><FontAwesomeIcon icon={faBuilding} />{' '}Компании</Nav.Link></Nav.Item>
                <Nav.Item><Nav.Link className={"side-bar-item"} as={Link} to="/materials"><FontAwesomeIcon icon={faHammer} />{' '}Стройматериалы</Nav.Link></Nav.Item>
            </Nav>
            }
        { !props.expanded &&
            <Nav className={"flex-column my-sidebar my-sidebar-collapsed"}>
                <Nav.Item className={""}><Nav.Link className={"side-bar-item-collapsed"} as={Link} to="/companies"><FontAwesomeIcon icon={faBuilding} size="2x" /></Nav.Link></Nav.Item>
                <Nav.Item className={""}><Nav.Link className={"side-bar-item-collapsed"} as={Link} to="/materials"><FontAwesomeIcon icon={faHammer} size="2x" /></Nav.Link></Nav.Item>
            </Nav>
            }
            </>
    )
}

export default SideBar;
