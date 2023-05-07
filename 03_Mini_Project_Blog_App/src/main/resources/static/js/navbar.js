document.getElementById("demo").innerHTML = `
<nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
        <div class="container-fluid ">
          <a class="navbar-brand text-white" href="/">Ashok it Blog App</a>
          
          <div class="collapse navbar-collapse  " id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link active text-white" aria-current="page" href="/">All Posts</a>
              </li>
              <li class="nav-item">
                <a class="nav-link text-white" href="registerPage">Register</a>
              </li>
              <li class="nav-item">
                <a class="nav-link text-white" href="loginpage">Login</a>
              </li>
              
            </ul>
            <form class="d-flex">
              <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
              <button class="btn btn-outline-light" type="submit">Search</button>
            </form>
          </div>
        </div>
      </nav>    
`;


