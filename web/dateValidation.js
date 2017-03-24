/**
 * Created by Sergiy.K on 24-Mar-17.
 */
function compareDate() {
    var start_date = $('[name="searchFields:options_form:startDate_input"]').val();
    var end_date = $('[name="searchFields:options_form:endDate_input"]').val();

    if (!start_date || !end_date) {
        return true;
    }

    start_date = new Date(start_date).getTime();
    end_date = new Date(end_date).getTime();

    if (end_date < start_date) {
        alert('startDate must be earlier then endDate');
    }
}

function formAction() {
    $('[name="searchFields:options_form:submitButton"]').click(function (event) {
        if (!compareDate()) {
            event.preventDefault();
        }
    });
}

formAction();