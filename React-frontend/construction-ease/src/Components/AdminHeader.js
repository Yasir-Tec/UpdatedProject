

import React from "react"
import { NavLink } from "react-router-dom"

const AdminHeaderComponent = () => {

    return(
            	
			<header className="header header-fixheight header--fixed">
				<div className="container">
					<div className="header__inner">
						<div className="header-logo"><a href="/"><img src="assets/img/logo_white.png" alt=""/></a></div>
						
					
						<nav className="raising-nav">
							
				
							<ul className="raising-menu">
								<li><a href="/user">UserList</a>
								</li>
								<li><a href="/contractor">Contractor List</a>
								</li>
                                <li><a href="/clientsPayment">Client Payment</a>
								</li>
                                <li><a href="/contractorfeedbacklist">Contractor Feedback</a>
								</li>
                                <li><a href="/clientfeedbacklist">Client Feedback</a>
								</li>
							</ul>
          
							<div className="navbar-toggle"><i class="fa fa-bars"></i></div>
						</nav>
						
					</div>
				</div>
			</header>
  
    )
}

export default AdminHeaderComponent;