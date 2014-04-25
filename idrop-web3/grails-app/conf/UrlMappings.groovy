class UrlMappings {

	static mappings = {

		"/login"(resource:"login")

		"/collection/$virtualCollection/$path?"{ resource:"collection"}

		"/$controller/$action?/$id?"{ constraints { // apply constraints here
			} }

		"500"(controller: "error")

		//		"/"(uri:"/index.html")
	}
}
