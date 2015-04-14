
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

            <div id="content" class="clearfix">
                <div id="page-content">

                    <div class="info">
                        <h2>Internal Error</h2>
                        <div>

                            <span class="small_signature" id="access_ip_info" style="display:"> <br> <br>
                                ${exception} <br>
                                ${exception.getMessage()}

                            </span>
                        </div>
                    </div>

                </div><!--body end-->
                <div id="aside">
                </div>    
            </div><!--content end-->

