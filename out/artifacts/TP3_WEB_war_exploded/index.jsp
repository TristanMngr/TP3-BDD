<%--
  Created by IntelliJ IDEA.
  User: tristanmenager
  Date: 21/05/2018
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>welcome page</title>
    <link rel="stylesheet" type="text/css" href="assets/css/semantic.css">
    <link rel="stylesheet" type="text/css" href="assets/css/custom.css">
</head>
<body>

<div class="ui container">
    <h1 class="ui center aligned sizer vertical huge header">TP3</h1>
    <i class="desktop icon"></i>

    <div class="ui stackable two column grid">
        <div class="column">
            <div class="ui clearing segment">
                <h2>PART I</h2>
                <h3 class="ui top attached block header">Display Department</h3>
                <form class="ui form bottom attached segment" action="/part-one" method="post">
                    <input type="hidden" value="display-department" name="dispatch">
                    <button class="ui primary button" type="submit">
                        Display department
                    </button>
                </form>

                <h3 class="ui top attached block header">Move employee to an other department</h3>
                <form class="ui form bottom attached segment" action="/part-one" method="post">
                    <div class="field">
                        <label>Employee to move (number)</label>
                        <input type="text" name="employee" placeholder="employee">
                    </div>
                    <div class="field">
                        <label>to department (number)</label>
                        <input type="text" name="department" placeholder="department">
                    </div>
                    <input type="hidden" value="move-employee" name="dispatch">
                    <button class="ui primary button" type="submit">Submit</button>
                </form>

                <h3 class="ui top attached block header">Display table</h3>
                <form class="ui form bottom attached segment" action="/part-one" method="post">
                    <div class="field">
                        <label>Table to display</label>
                        <input type="text" name="table" placeholder="employee">
                    </div>
                    <input type="hidden" value="display-table" name="dispatch">
                    <button class="ui primary button" type="submit">Submit</button>
                </form>
            </div>
        </div>

        <div class="column">
            <div class="ui clearing segment">
                <h2>PART 2</h2>
                <h3 class="ui top attached block header">Find Department</h3>
                <form class="ui form bottom attached segment" action="/part-two/department" method="get">
                    <div class="field">
                        <label>Find departement</label>
                        <input type="text" name="department-id" placeholder="id department">
                    </div>
                    <button class="ui primary button" type="submit">Submit</button>
                </form>

                <h3 class="ui top attached block header">Find Employee</h3>
                <form class="ui form bottom attached segment" action="/part-two/employee" method="get">
                    <div class="field">
                        <label>Find employee</label>
                        <input type="text" name="employee-id" placeholder="id employee">
                    </div>
                    <button class="ui primary button" type="submit">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>

</div>
</body>
</html>
