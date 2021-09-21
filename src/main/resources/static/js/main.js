const hideMessage = id => {
  document.getElementById(id).classList.add('hidden');
}

const showMessage = id => {
  document.getElementById(id).classList.remove('hidden');
}

const submitExportCardForm = (controllerURI, successMessageId, successDescriptionId, errorMessageId, data) => {
  postRequest(controllerURI, data)
    .then(responseJson => showExportSuccessMessage(responseJson, successMessageId, successDescriptionId))
    .catch(error => showExportErrorMessage(error, errorMessageId))
}

const postRequest = (uri, data) => {
  return fetch(uri, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
    .then(response => {
      if (response.status > 400) {
        throw new Error('non-200 status', response);
      }
      return response.json();
    })
}

const showExportSuccessMessage = (responseJson, successMessageId, successDescriptionId) => {
  const affectedFileNames = responseJson.affectedFileNames;
  document.getElementById(successDescriptionId).innerHTML =
    `Here are list of affected files:<br>${affectedFileNames.join("<br>")}`
  showMessage(successMessageId);
}

const showExportErrorMessage = (error, errorMessageId) => {
  console.log(error);
  showMessage(errorMessageId);
}
