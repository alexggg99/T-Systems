<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div id="content" class="clearfix">
                <div id="page-content">

                    <form class="pure-form pure-form-aligned" action="getRoutesEnteties" method="POST">
                        <fieldset>
                            <div class="pure-control-group">
                                <label for="cityFrom"><spring:message code="home.cityFrom" /></label>
                                <input id="cityFrom" type="text" placeholder="Saint-Petersburg" name="cityFrom"
                                       value="${cityFrom}" required>
                            </div>

                            <div class="pure-control-group">
                                <label for="cityTo"><spring:message code="home.cityTo" /></label>
                                <input id="cityTo" type="text" placeholder="" name="cityTo" 
                                       value="${cityTo}" required>
                            </div>

                            <div class="pure-control-group">
                                <label for="depatureDate"><spring:message code="home.date" /></label>
                                <input id="depatereDate" type="date" placeholder="" name="depatureDate" 
                                       value="${depatureDate}" required>
                            </div>

                            <div class="pure-controls">

                                <button class="pure-button"><spring:message code="home.find" /></button>
                            </div>
                        </fieldset>
                    </form>

                </div><!--body end-->
                <div id="aside">
                    <form class="pure-form" action="showTrainsOnStation" method="POST">
                        <input type="text" class="pure-input-rounded" name="station">
                        <button type="submit" class="pure-button"><spring:message code="home.search" /></button>
                    </form>
                </div>    
            </div>