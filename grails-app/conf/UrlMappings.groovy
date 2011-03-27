class UrlMappings {

	static mappings = {
        "/login/$action?"(controller: "login")
        "/logout/$action?"(controller: "logout")
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
        "/security/$controller/$action?/$id?" {
            controller = $controller
            action = $action
            id = $id
            constraints {
                controller(matches:/user|role/)
            }
        }
		"/"(view:"/index")
		"500"(view:'/error')
	}
}
