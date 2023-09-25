package com.example.gitlabwebhooks.api;

import com.example.gitlabwebhooks.api.json.WebhookJson;
import com.example.gitlabwebhooks.api.json.WebhookJsonAssembler;
import com.example.gitlabwebhooks.domain.*;
import com.example.gitlabwebhooks.service.MergeRequestDto;
import com.example.gitlabwebhooks.service.WebhookDto;
import com.example.gitlabwebhooks.service.WebhookService;
import com.example.gitlabwebhooks.service.message.WebhookRequest;
import com.example.gitlabwebhooks.service.message.WebhookResponse;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/webhook")
public class WebhookController {
    private WebhookService webhookService;
    public WebhookController(WebhookService webhookService) { this.webhookService = webhookService; };
    @PostMapping
    public ResponseEntity<String> save(@RequestBody String requestBody) {
        JSONObject jsonObj = new JSONObject(requestBody);
        String objectKind = jsonObj.getString("object_kind");
        String timestamp = Instant.now().toString();
        Optional<JSONObject> projectObj = Optional.ofNullable(jsonObj.getJSONObject("project"));
        Optional<JSONObject> objectAttributesObj = Optional.ofNullable(jsonObj.optJSONObject("object_attributes"));
        Project project = projectObj.map(obj -> new Project(
                obj.optInt("id"),
                obj.optString("homepage", ""),
                obj.optString("default_branch", ""),
                obj.optString("name", ""),
                obj.optString("ref", ""),
                obj.optString("description", ""),
                obj.optString("web_url", ""),
                obj.optString("git_ssh_url", ""),
                obj.optString("git_http_url", ""),
                obj.optInt("visibility_level"),
                obj.optString("url", ""),
                obj.optString("ssh_url", ""),
                obj.optString("http_url", "")
        )).orElse(null);
        ObjectAttributes objectAttributes = objectAttributesObj.map(obj -> new ObjectAttributes(
                obj.optInt("id"),
                obj.optString("created_at", ""),
                obj.optString("updated_at", ""),
                obj.optString("description", ""),
                obj.optString("url", ""),
                obj.optInt("updated_by_id"),
                obj.optInt("author_id"),
                obj.optString("resolved_by_push", "")
        )).orElse(null);
        Optional<JSONObject> mergeRequest;
        if (objectKind.equals("merge_request")) {
            mergeRequest = Optional.ofNullable(jsonObj);
            MergeRequestObjectAttributes attributes = new MergeRequestObjectAttributes();
            Author author = new Author();
            LastCommit lastCommit = new LastCommit();
            Source source = new Source();
            MergeParams mergeParams = new MergeParams();
            Target target = new Target();
            Repository repository = new Repository();
            User user = new User();
            mergeRequest.map(obj -> {

                attributes.setMerge_when_pipeline_succeeds(obj.optBoolean("merge_when_pipeline_succeeds"));
                JSONObject mergeReqObjAttr = obj.getJSONObject("object_attributes");

                JSONObject authorJson = mergeReqObjAttr.getJSONObject("last_commit").getJSONObject("author");
                author.setName(authorJson.optString("name"));
                author.setEmail(authorJson.optString("email"));

                JSONObject lastCommitJson = mergeReqObjAttr.getJSONObject("last_commit");
                lastCommit.setLast_commit_id(lastCommitJson.optString("id"));
                lastCommit.setAuthor(author);
                lastCommit.setMessage(lastCommitJson.optString("message"));
                lastCommit.setTitle(lastCommitJson.optString("title"));
                lastCommit.setUrl(lastCommitJson.optString("url"));
                lastCommit.setTimestamp(lastCommitJson.optString("timestamp"));

                attributes.setLast_commit(lastCommit);
                attributes.setDetailed_merge_status(mergeReqObjAttr.optString("detailed_merge_status"));
                attributes.setIid(mergeReqObjAttr.optInt("iid"));
                attributes.setMerge_user_id(mergeReqObjAttr.optString("merge_user_id"));
                attributes.setMilestone_id(mergeReqObjAttr.optString("milestone_id"));
                attributes.setCreated_at(mergeReqObjAttr.optString("created_at"));
                attributes.setDescription(mergeReqObjAttr.optString("description"));
                attributes.setObject_attributes_id(mergeReqObjAttr.optInt("id"));

                JSONObject sourceJson = mergeReqObjAttr.getJSONObject("source");
                source.setSource_id(sourceJson.optInt("source_id"));
                source.setPath_with_namespace(sourceJson.optString("path_with_namespace"));
                source.setSsh_url(sourceJson.optString("ssh_url"));
                source.setDescription(sourceJson.optString("description"));
                source.setGit_http_url(sourceJson.optString("git_http_url"));
                source.setGit_ssh_url(sourceJson.optString("git_ssh_url"));
                source.setUrl(sourceJson.optString("url"));
                source.setHttp_url(sourceJson.optString("http_url"));
                source.setCi_config_path(sourceJson.optString("ci_config_path"));
                source.setWeb_url(sourceJson.optString("web_url"));
                source.setAvatar_url(sourceJson.optString("avatar_url"));
                source.setName(sourceJson.optString("name"));
                source.setNamespace(sourceJson.optString("namespace"));
                source.setVisibility_level(sourceJson.optInt("visibility_level"));
                source.setDefault_branch(sourceJson.optString("default_branch"));
                source.setHomepage(sourceJson.optString("homepage"));

                attributes.setSource(source);

                mergeParams.setShould_remove_source_branch(obj.optBoolean("should_remove_source_branch"));
                mergeParams.setForce_remove_source_branch(obj.optInt("force_remove_source_branch"));

                attributes.setMerge_params(mergeParams);

                JSONObject targetJson = mergeReqObjAttr.getJSONObject("target");
                target.setTarget_id(targetJson.optInt("target_id"));
                target.setPath_with_namespace(targetJson.optString("path_with_namespace"));
                target.setSsh_url(targetJson.optString("ssh_url"));
                target.setDescription(targetJson.optString("description"));
                target.setGit_http_url(targetJson.optString("git_http_url"));
                target.setGit_ssh_url(targetJson.optString("git_ssh_url"));
                target.setUrl(targetJson.optString("url"));
                target.setHttp_url(targetJson.optString("http_url"));
                target.setCi_config_path(targetJson.optString("ci_config_path"));
                target.setWeb_url(targetJson.optString("web_url"));
                target.setAvatar_url(targetJson.optString("avatar_url"));
                target.setName(targetJson.optString("name"));
                target.setNamespace(targetJson.optString("namespace"));
                target.setVisibility_level(targetJson.optInt("visibility_level"));
                target.setDefault_branch(targetJson.optString("default_branch"));
                target.setHomepage(targetJson.optString("homepage"));

                attributes.setTarget(target);


                JSONObject repositoryJson = obj.getJSONObject("repository");
                repository.setName(repositoryJson.optString("name"));
                repository.setDescription(repositoryJson.optString("description"));
                repository.setUrl(repositoryJson.optString("url"));
                repository.setHomepage(repositoryJson.optString("homepage"));


                JSONObject userJson = obj.getJSONObject("user");
                user.setUser_id(userJson.optInt("id"));
                user.setAvatar_url(userJson.optString("avatar_url"));
                user.setName(userJson.optString("name"));
                user.setEmail(userJson.optString("email"));
                user.setUsername(userJson.optString("username"));

              return null;
            }).orElse(null);

            MergeRequestDto mergeRequestDto = new MergeRequestDto(
                    jsonObj.optInt("merge_request_id"),
                    attributes,
                    jsonObj.optString("changes"),
                    project,
                    repository,
                    user,
                    objectKind
            );
        }

        WebhookDto webhookDTO = new WebhookDto(
                1,
                jsonObj.getString("object_kind"),
                timestamp,
                project,
                objectAttributes
        );
        webhookService.saveWebhookData(webhookDTO);
        return new ResponseEntity<>(requestBody, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<WebhookJson>> getWebhookData() {
        WebhookRequest webhookRequest = WebhookRequest.of().build();
        WebhookResponse webhookResponse = webhookService.getWebhookData(webhookRequest);
        return ResponseEntity.ok(WebhookJsonAssembler.toWebhookJsonList(webhookResponse.getWebhookData()));
    }
}
