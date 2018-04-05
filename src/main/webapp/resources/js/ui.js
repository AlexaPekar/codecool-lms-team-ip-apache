class UI {
  constructor() {
    this.profile = document.getElementById('profile');
  }


  showProfile(user) {
    this.profile.innerHTML = `
      <div class="card card-body mb-3">
        <form action="github" method="Post">
        <div class="row">
          <div class="col-md-3">
            <img class="img-fluid mb-2" src="${user.avatar_url}">
            <a href="${user.html_url}" target="_blank" class="btn btn-primary btn-block mb-4">View Profile</a>
          </div>
          <div class="col-md-9">
            <span class="badge badge-primary">Public Repos: ${user.public_repos}</span>
            <span class="badge badge-secondary">Public Gists: ${user.public_gists}</span>
            <span class="badge badge-success">Followers: ${user.followers}</span>
            <span class="badge badge-info">Following: ${user.following}</span>
            <br><br>
            <ul class="list-group">
              <li class="list-group-item">Company: ${user.company}</li>
              <li class="list-group-item">Website/Blog: ${user.blog}</li>
              <li class="list-group-item">Location: ${user.location}</li>
              <li class="list-group-item">Member Since: ${user.created_at}</li>
            </ul>
          </div>
        </div>

      <h3 class="page-heading mb-3">Latest Repos</h3>
      <div id="repos"></div>
      <input type="hidden" name="avatar" value="${user.avatar_url}">
      <input type="hidden" name="html" value="${user.html_url}">
      <input type="hidden" name="repos" value="${user.public_repos}">
      <input type="hidden" name="gists" value="${user.public_gists}">
      <input type="hidden" name="followers" value="${user.followers}">
      <input type="hidden" name="following" value="${user.following}">
      <input type="hidden" name="company" value="${user.company}">
      <input type="hidden" name="blog" value="${user.blog}">
      <input type="hidden" name="location" value="${user.location}">
      <input type="hidden" name="created" value="${user.created_at}">
      </div>
    `;
  }


  showRepos(repos) {
    let output = '';

    repos.forEach(function(repo) {
      output += `
        <div class="card card-body mb-2">
          <div class="row">
            <div class="col-md-6">
              <a class="repo-name" href="${repo.html_url}" target="_blank">${repo.name}</a>
            </div>
            <div class="col-md-6">
            <span class="badge badge-primary">Stars: ${repo.stargazers_count}</span>
            <span class="badge badge-secondary">Watchers: ${repo.watchers_count}</span>
            <span class="badge badge-success">Forks: ${repo.forms_count}</span>

            <input type="hidden" name="repo-html" value="${repo.html_url}">
            <input type="hidden" name="repo-name" value="${repo.name}">
            <input type="hidden" name="repo-star" value="${repo.stargazers_count}">
            <input type="hidden" name="repo-watcher" value="${repo.watchers_count}">
            <input type="hidden" name="repo-forms" value="${repo.forms_count}">

            </div>
          </div>
        </div>
      `;
    });
    output += `
    <input type="submit" value="Connect" class="button">
    </form>
    `;

    document.getElementById('repos').innerHTML = output;

  }


  showAlert(message, className) {

    this.clearAlert();

    const div  =  document.createElement('div');

    div.className = className;

    div.appendChild(document.createTextNode(message));

    const container =  document.querySelector('.searchContainer');

    const search = document.querySelector('.search');

    container.insertBefore(div, search);


    setTimeout(() => {
      this.clearAlert();
    }, 3000);
  }


  clearAlert() {
    const currentAlert = document.querySelector('.alert');

    if(currentAlert){
      currentAlert.remove();
    }
  }


  clearProfile() {
    this.profile.innerHTML = '';
  }
}