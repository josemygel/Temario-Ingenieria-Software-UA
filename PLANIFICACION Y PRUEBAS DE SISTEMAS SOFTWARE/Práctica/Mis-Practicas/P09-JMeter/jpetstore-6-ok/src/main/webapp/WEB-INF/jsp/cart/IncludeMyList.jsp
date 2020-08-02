<%--

       Copyright 2010-2016 the original author or authors.

       Licensed under the Apache License, Version 2.0 (the "License");
       you may not use this file except in compliance with the License.
       You may obtain a copy of the License at

          http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing, software
       distributed under the License is distributed on an "AS IS" BASIS,
       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
       See the License for the specific language governing permissions and
       limitations under the License.

--%>
<c:if test="${!empty accountBean.myList}">
	<p>Pet Favorites <br />
	Shop for more of your favorite pets here.</p>
	<ul>
		<c:forEach var="product" items="${accountBean.myList}">
			<li><stripes:link
				beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"
				event="viewProduct">
				<stripes:param name="productId" value="${product.productId}" />
			${product.name}
		</stripes:link> (${product.productId})</li>
		</c:forEach>
	</ul>

</c:if>
