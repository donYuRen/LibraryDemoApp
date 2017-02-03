<div id="allUsers" class="container">
  <#list users as user>
    <h3>${user?counter}.: ${user.name}</h3>
    <div id="user_book_${user.id}">
       <ul>Age: ${user.age}</ul>
       <ul>Sex: ${user.age}</ul>
       <button id="chk_bk_${user.id}">Check My Borrowed books</button>
       <div id="chk_bk_dtls_${user.id}">
       </div>
    </div>
  </#list>
</div>

  <script src="js/app/users.js"></script>