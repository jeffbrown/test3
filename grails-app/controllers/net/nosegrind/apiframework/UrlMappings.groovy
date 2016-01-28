package net.nosegrind.apiframework


class UrlMappings {

    static mappings = {
        String apiVersion = getGrailsApplication().config.getProperty('info.app.version')

        String entryPoint = "v${apiVersion}"

        // BATCH API ENDPOINTS
        "/$entryPoint/$controller/$action?/$id?**"{
            parseRequest = true
        }

        "/$batchEntryPoint/$controller/$action" {
            parseRequest = true
        }
    }
}
