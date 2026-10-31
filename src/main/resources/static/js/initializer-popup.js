    export function InitializerPopup(){

        document.getElementById("doc_num").addEventListener("input", function (event){
            checkDocNumber(event.target);
        });

        const departList = JSON.parse(document.getElementById('popupData').dataset.depsList) ;

        $('#sender_page')
            .select2( { width:'70%', dropdownParent: $("#globalModal") })
            .on('change', function () {
                // document.getElementById("sender_name").value = "rank name surname";
                getRespPersone(this, document.getElementById("sender_name"), departList);
            });

        $('#receiver_page')
            .select2( { width:'70%', dropdownParent: $("#globalModal") })
            .on('change', function () {
                console.log('--------------> search for resp person' + this.value);
                getRespPersone(this, document.getElementById('receiver_name'), departList);
            });

        // document.getElementById("sender_page").addEventListener("change", function () {
        //     console.log('-------------->' + this.value);
        //     getRespPersone(this, sender_name, departList);
        // });

        // document.getElementById("receiver_page").addEventListener("change", function () {
        //     console.log('-------------->' +  this.value);
        //     getRespPersone(this, sender_name, departList);
        // });



        flatpickr("#data_doc", {
            enableTime: true, time_24hr: true, dateFormat: "d.m.Y H:i", defaultDate: new Date(),
            onChange: function (selectedDates, dateStr, instance) {
                document.getElementById('data_doc_oper').value = dateStr;
                let date = new Date(selectedDates[0]);
                date.setDate(date.getDate() + 3);
                let day = String(date.getDate()).padStart(2,'0');
                let month = String(date.getMonth()+1).padStart(2,'0');
                let year = String(date.getFullYear());
                let hour = String(date.getHours()).padStart(2,'0');
                let minutes = String(date.getMinutes()).padStart(2,'0');

                document.getElementById('data_doc_during').value = `${day}.${month}.${year} ${hour}:${minutes}`
            }
        });
    }//end function

    export function InitFormGetNewCardNumber(){
        const spinnerLoading = $('#spinner_loading');


        $('#btn_get_next_num_card').on('click', function () {
            $('#card_number').val('');
            spinnerLoading.prop('hidden', false);

            fetch('/cards/ajax/last_card_number', {method: 'GET'})
                .then(res => {
                    if (!res.ok){throw new Error('error: ' + res.status)}
                    return res.text();
                })
                .then(async resNumber=> {
                    await new Promise(resolve => setTimeout(resolve, 100));
                    $('#card_number').val((parseInt(resNumber) + 1).toString().padStart(5, '0'));
                    $('#card_number').css('backgroundColor', '#e6ffee')
                })
                .catch(err => { console.error('error fetching card number:', err) })
                .finally(() => { spinnerLoading.prop('hidden', true) });
        });

        $('#btn_found_card_number').on('click', function () {
           const cardNumber = $('#card_number')
            if (cardNumber.val() !== '' && Number(cardNumber.val())>0 && Number(cardNumber.val())<=99999){
                cardNumber.css('backgroundColor','#e6ffee');
                console.log('search card number does not work');
            }else{
                cardNumber.css('backgroundColor','#ffe6e6');

            }
        });
        
        $('#btn-create-new-card').on('click', function (e) {
            e.preventDefault();

            fetch('/cards/simple-create-new-card',
                {method:'POST',
                body: new URLSearchParams({cardNumber: $('#card_number').val(), cardDate: $('#created_at').val()})
                })
                .then(promise => async function (){
                        return await promise.json();
                    })
                .then(card =>{
                    console.log(card);
                    $('#card_number_added').val(card.cardNumber);
                    $('#unit_card_number_id').val(card.id);
                });
        });
    }//end function