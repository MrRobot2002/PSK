package lt.vu.usecases;

import lt.vu.interceptors.LoggedInvocation;
import lt.vu.services.JerseyNumberGenerator;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GeneratePlayerJerseyNumber implements Serializable {
    @Inject
    JerseyNumberGenerator jerseyNumberGenerator;

    private CompletableFuture<Integer> jerseyNumberGenerationTask = null;

    @LoggedInvocation
    public String generateNewJerseyNumber() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        jerseyNumberGenerationTask = CompletableFuture.supplyAsync(() -> jerseyNumberGenerator.generateJerseyNumber());

        return  "/playerDetails.xhtml?faces-redirect=true&playerId=" + requestParameters.get("playerId");
    }

    public boolean isJerseyGenerationRunning() {
        return jerseyNumberGenerationTask != null && !jerseyNumberGenerationTask.isDone();
    }

    public String getJerseyGenerationStatus() throws ExecutionException, InterruptedException {
        if (jerseyNumberGenerationTask == null) {
            return null;
        } else if (isJerseyGenerationRunning()) {
            return "Jersey generation in progress";
        }
        return "Suggested jersey number: " + jerseyNumberGenerationTask.get();
    }
}
