import Toastify from 'toastify-js'
import 'toastify-js/src/toastify.css'

export const showToast = (text, type = 'success') => {
    Toastify({
        text,
        duration: 3000,
        gravity: 'top',
        position: 'center',
        backgroundColor: type === 'success' ? '#28a745' : '#dc3545'
    }).showToast()
}